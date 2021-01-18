import React, {useEffect, useState} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import CssBaseline from '@material-ui/core/CssBaseline';
import Drawer from '@material-ui/core/Drawer';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';
import IconButton from '@material-ui/core/IconButton';
import Container from '@material-ui/core/Container';
import Grid from '@material-ui/core/Grid';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import ListItem from "@material-ui/core/ListItem";
import ListItemIcon from "@material-ui/core/ListItemIcon";
import ListItemText from "@material-ui/core/ListItemText";
import SecurityIcon from "@material-ui/icons/Security";
import SettingsIcon from "@material-ui/icons/Settings";
import {navigate} from "@reach/router";
import Button from "@material-ui/core/Button";
import TextField from '@material-ui/core/TextField';
import Link from '@material-ui/core/Link';
import InputLabel from '@material-ui/core/InputLabel';
import Select from '@material-ui/core/Select';
import {FormControl} from '@material-ui/core';
import {backendUrl} from "../Constants";




const Visits = props => {

    const classes = useStyles();
    const [id, setId] = useState("");
    const [visitDate,setVisitDate] = useState("");
    const [selectedPatient, setSelectedPatient] = useState(undefined);
    const [patientsList, setPatientsList] = useState([]);
    const [selectedPhysician, setSelectedPhysician] = useState(undefined);
    const [physiciansList, setPhysiciansList] = useState([]);
    const [reason, setReason] = useState("");
    const [endOfProcess, setEndOfProcess] = useState(false);
    const [loading, setLoading] = React.useState(false);
    const [status, setStatus] = useState("");
    const [error, setError] = useState(null);
    const [idError, setIdError] = useState('');
    const [visitDateError, setVisitDateError] = useState('');


    const onChangeHandler = event => {
        const { name, value } = event.target;
        if (name === "id") {
            setId(value);
        }else if (name === "visitDate") {
            setVisitDate(value);
        }else if (name === "patient") {
            setSelectedPatient(value);
        }else if (name === "physician") {
            setSelectedPhysician(value);
        }else if (name === "reason") {
            setReason(value);
        }
    };

    const onclickMethodTabs = (event,tab) => {
        console.log("Navigating to"+tab)
        event.preventDefault();
        getNavigationTabs(tab);

    };

    useEffect(() => {
        fetch(backendUrl+"physicians/")
            .then(res => res.json())
            .then(data => {
                console.log(data);
                setPhysiciansList(data);
            });

    }, );

    useEffect(() => {
        fetch(backendUrl+"patients/")
            .then(res => res.json())
            .then(data => {
                console.log(data);
                setPatientsList(data);
            });

    }, );


    const buttonOnclickMethod = async (event) => {
        setLoading(true);
        setIdError('');
        setVisitDateError('');
        setStatus('');
        event.preventDefault();
        if (id === '') {
            setIdError('Id is Required.');
            if (visitDate === '')
                setVisitDateError('Visit Date is Required.');
        }
        else if (visitDate === '') {
            setVisitDateError('Visit Date is Required.');
        }
        else  {
            try {
                const data = {
                    "id": id,
                    "visitDatetime": visitDate,
                    "patientId": selectedPatient.id,
                    "physicianId": selectedPhysician.id,
                    "reason": reason
                }
                const visit = await createVisit(data);
                setEndOfProcess(true);
                setLoading(false);
                if (visit != null) {
                    if (visit.id != null)
                        setStatus('Visit is created Successfully');
                    else if(visit.description)
                        setStatus(visit.description);
                    else
                        setStatus(visit.error);
                } else
                    setStatus('Something happened wrong');

            } catch (error) {
                setError('Error in Creating Visit');
                setStatus(error);
            }
        }
        setLoading(false);
        setId("");
        setVisitDate("");
        setSelectedPatient(undefined);
        setSelectedPhysician(undefined);
        setReason("");
    };
    return (
        <div className={classes.root}>
            <CssBaseline />
            <Drawer
                variant="permanent">
                <div className={classes.toolbarIcon}>
                    <IconButton >
                        <ChevronLeftIcon />
                    </IconButton>
                </div>
                <Divider />
                <div>
                    <ListItem button onClick = {(event) => {
                        onclickMethodTabs(event,'/')}}
                    >
                        <ListItemIcon>
                            <SecurityIcon/>
                        </ListItemIcon>
                        <ListItemText primary="Patients"/>
                    </ListItem>

                    <ListItem button onClick = {(event) => {
                        onclickMethodTabs(event,'physicians')}}
                    >
                        <ListItemIcon>
                            <SecurityIcon/>
                        </ListItemIcon>
                        <ListItemText primary="Physicians"/>
                    </ListItem>

                    <ListItem>
                        <ListItemIcon>
                            <SettingsIcon/>
                        </ListItemIcon>
                        <ListItemText primary="Visits"/>
                    </ListItem>
                </div>
                <Divider />


            </Drawer>
                <Container component="main" maxWidth="xs">
                    <form className={classes.form} noValidate>
                        <Grid container spacing={2}>
                            <Typography component="h1" variant="h4" color="inherit" gutterBottom>
                                Visit Creation
                            </Typography>
                            <Grid item xs={12} >
                                <TextField
                                    autoComplete="id"
                                    name="id"
                                    value={id}
                                    variant="outlined"
                                    required
                                    fullWidth
                                    id="id"
                                    label="ID"
                                    autoFocus
                                    helperText={idError}
                                    error = {idError === '' ?false:true}
                                    onChange={event => onChangeHandler(event)}
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <TextField
                                    id="visitDate"
                                    name="visitDate"
                                    value={visitDate}
                                    label="Visit Date"
                                    type="datetime-local"
                                    defaultValue="yyyy-mm-ddTHH:MM"
                                    helperText={visitDateError}
                                    error = {visitDateError === '' ?false:true}
                                    className={classes.textField}
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                    onChange={event => onChangeHandler(event)}
                                />
                            </Grid>
                            <Grid item xs={12} >
                                <FormControl className={classes.formControl}  >
                                    <InputLabel shrink htmlFor="age-native-label-placeholder">
                                        Patient
                                    </InputLabel>
                                    <Select
                                        onChange={(event) => onChangeHandler(event)}
                                        inputProps={{
                                            name: 'patient',
                                            id: 'age-native-label-placeholder',
                                        }}
                                        renderValue={()=>{
                                            return (<text>{selectedPatient ? selectedPatient.name : ""}</text>)
                                        }}
                                    >{patientsList.map(obj => (
                                        <option
                                            key={obj}
                                            value={obj} >
                                            {obj.name}</option>
                                    )) }
                                    </Select>
                                </FormControl>
                            </Grid>
                            <Grid item xs={12} >
                                <FormControl className={classes.formControl}  >
                                    <InputLabel shrink htmlFor="age-native-label-placeholder">
                                        Physician
                                    </InputLabel>
                                    <Select
                                        onChange={(event) => onChangeHandler(event)}
                                        inputProps={{
                                            name: 'physician',
                                            id: 'age-native-label-placeholder',
                                        }}
                                        renderValue={()=>{
                                            return (<text>{selectedPhysician ? selectedPhysician.name : ""}</text>)
                                        }}
                                    >{physiciansList.map(obj => (
                                        <option
                                            key={obj}
                                            value={obj} >
                                            {obj.name}</option>
                                    )) }
                                    </Select>
                                </FormControl>
                            </Grid>
                            <Grid item xs={12} >
                                <TextField
                                    variant="outlined"
                                    required
                                    fullWidth
                                    id="reason"
                                    label="Reason"
                                    name="reason"
                                    value={reason}
                                    autoComplete="reason"
                                    onChange={event => onChangeHandler(event)}
                                />
                            </Grid>

                            <Button
                                disabled = {loading}
                                type="submit"
                                fullWidth
                                variant="contained"
                                color="primary"
                                className={classes.submit} onClick={event => {
                                buttonOnclickMethod(event);
                            }}
                            >
                                SUBMIT
                            </Button>
                            <Grid container justify="flex-end">
                                <Grid item>
                                    <Link href="visit_profile" variant="body2">
                                        Already have an account? Find Visit >>>
                                    </Link>
                                </Grid>
                            </Grid>
                            <Grid item xs={12} sm={100}>
                                <div className = "md:pl-4">
                                    {endOfProcess &&
                                    <p className={classes.form}>{status}</p>
                                    }

                                </div>
                            </Grid>
                        </Grid>
                    </form>
                </Container>
        </div>
    );


}
function getNavigationTabs(tab) {
    navigate(tab);
}
function createVisit(pData) {
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(pData)
    };
    return fetch(backendUrl+'visit/', requestOptions)
        .then(response => response.json())
        .then(data => {
            if(data !=null){
                console.log(data.description);
                return data;
            }else {
                console.log('Something happened wrong');
            }
        });
}

const useStyles = makeStyles((theme) => ({
    root: {
        display: 'flex',
    },
    title: {
        flexGrow: 2,
    },
    content: {
        flexGrow: 1,
        height: '100vh',
        overflow: 'auto',
    },
    container: {
        paddingTop: theme.spacing(4),
        paddingLeft: theme.spacing(2),
        paddingBottom: theme.spacing(4),
    },
    paper: {
        padding: theme.spacing(2),
        display: 'flex',
        overflow: 'auto',
        flexDirection: 'column',
    },
    fixedHeight: {
        height: 500,
    },
    form: {
        width: '100%',
        marginTop: theme.spacing(2),
    },
    formControl:{
        padding: theme.spacing(1),
        display: 'flex',
        overflow: 'auto',
        flexDirection: 'column',
        paddingBottom: theme.spacing(2),
        paddingTop: theme.spacing(1),
    },

}));

export default Visits;