import React, { useState} from 'react';
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
import FormControlLabel from '@material-ui/core/FormControlLabel';
import {FormControl,FormLabel,Radio} from '@material-ui/core';
import RadioGroup from '@material-ui/core/RadioGroup';
import Link from '@material-ui/core/Link';
import {backendUrl} from "../Constants";


const Patients = props => {

    const classes = useStyles();

    const [id, setId] = useState("");
    const [name, setName] = useState("");
    const [age, setAge] = useState("");
    const [gender, setGender] = useState("");
    const [endOfProcess, setEndOfProcess] = useState(false);
    const [loading, setLoading] = React.useState(false);
    const [status, setStatus] = useState('');
    const [error, setError] = useState(null);
    const [idError, setIdError] = useState('');
    const [nameError, setNameError] = useState('');
    const [ageError, setAgeError] = useState('');

    const onChangeHandler = event => {
        const { name, value } = event.currentTarget;
        if (name === "id") {
            setId(value);
        }else if (name === "name") {
            setName(value);
        }else if (name === "age") {
            setAge(value);
        }else if (name === "gender") {
                setGender(value);
        }
    };
    const onclickMethodTabs = (event,tab) => {
        console.log("Navigating to"+tab)
        event.preventDefault();
        getNavigationTabs(tab);

    };
    

    const buttonOnclickMethod = async(event) => {
        event.preventDefault();
        setLoading(true);
        setIdError('');
        setNameError('');
        setAgeError('');
        setStatus('');
        if (id === '') {
            setIdError('Id is Required.');
            if (name === '') {
                setNameError('Name is Required.');
            }
        }
        else if (name === '') {
            setNameError('Name is Required.');
        }
        else if(!Number(age)){
            setAgeError('Age should be in Numeric.');
        }
        else{
            try {
                const data = {
                    "id": id,
                    "name": name,
                    "age": age,
                    "gender": gender,

                }
                const patient = await createPatient(data);
                setEndOfProcess(true);
                setLoading(false);
                if (patient != null) {
                    if (patient.id != null)
                        setStatus("Patient is created Successfully");
                    else if(patient.description)
                        setStatus(patient.description);
                    else
                        setStatus(patient.error);
                } else
                    setStatus('Something happened wrong');
            } catch (error) {
                setError('Error in Creating Patient');
                setStatus(error);
            }
        }
        setLoading(false);
        setId("");
        setName("");
        setAge("");
        setGender("");
    };
    return (
        <div className={classes.root}>
            <CssBaseline />
            <Drawer
                variant="permanent"
            >
                <div className={classes.toolbarIcon}>
                    <IconButton >
                        <ChevronLeftIcon />
                    </IconButton>
                </div>
                <Divider />
                <div>
                    <ListItem button>
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

                    <ListItem button onClick = {(event) => {
                        onclickMethodTabs(event,'visits')}}
                    >
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
                                Patient Creation
                            </Typography>
                            <Grid item xs={12} >
                                <TextField
                                    autoComplete="id"
                                    name="id"
                                    value={id}
                                    variant="outlined"
                                    fullWidth
                                    id="id"
                                    label="ID"
                                    autoFocus
                                    helperText={idError}
                                    error = {idError === '' ?false:true}
                                    onChange={event => onChangeHandler(event)}
                                />
                            </Grid>
                            <Grid item xs={12} >
                                <TextField
                                    variant="outlined"
                                    required
                                    fullWidth
                                    id="name"
                                    label="Name"
                                    name="name"
                                    value={name}
                                    autoComplete="name"
                                    helperText={nameError}
                                    error = {nameError === '' ?false:true}
                                    onChange={event => onChangeHandler(event)}
                                />
                            </Grid>
                            <Grid item xs={12} >
                                <TextField
                                    variant="outlined"
                                    //required
                                    fullWidth
                                    id="age"
                                    pattern="[0-9]*"
                                    label="Age"
                                    name="age"
                                    value={age}
                                    autoComplete="age"
                                    helperText={ageError}
                                    error = {ageError === '' ?false:true}
                                    onChange={event => onChangeHandler(event)}
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <FormControl component="fieldset">
                                    <FormLabel component="legend">Gender</FormLabel>
                                    <RadioGroup aria-label="gender" name="gender" value={gender} onChange={event => onChangeHandler(event)}>
                                        <FormControlLabel value="F" control={<Radio />} label="Female" />
                                        <FormControlLabel value="M" control={<Radio />} label="Male" />
                                        <FormControlLabel value="O" control={<Radio />} label="Other" />
                                    </RadioGroup>
                                </FormControl>
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
                                    <Link href="profile" variant="body2">
                                        Already have an account? Find Patient >>>
                                    </Link>
                                </Grid>
                            </Grid>
                            <Grid item xs={12} sm={12}>
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
    navigate(tab)
}
function createPatient(pData) {
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(pData)
    };
    return fetch(backendUrl+'patients/', requestOptions)
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
}));

export default Patients;