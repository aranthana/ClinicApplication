import React,{ useState } from 'react';
import clsx from 'clsx';
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
import EdiText from 'react-editext';
import {backendUrl} from "../Constants";



const Profile = props => {

    const classes = useStyles();
    const [id, setId] = useState('');
    const [patient, setPatient] = useState(null);
    const [patientExist, setPatientExist] = useState(false);
    const [error, setError] = useState(null);

    const onChangeHandler = event => {
        const { name, value } = event.currentTarget;
        if (name === "id") {
            setId(value);
        }
    };
    const onclickMethodTabs = (event,tab) => {
        console.log("Navigating to"+tab)
        event.preventDefault();
        getNavigationTabs(tab);

    };

    const buttonOnclickMethod = async (event) => {
        event.preventDefault();
        setPatientExist(false);
        try{
            const data = {
                "id":id,
            }
            const patient =await getPatient(data);
            if(patient!=null){
                setPatient(patient);
                setPatientExist(true);
            }
        }
        catch(error){
            setError('Error Signing up with email and password');
        }
        setId("");

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
                           Find Patient
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
                                onChange={event => onChangeHandler(event)}
                            />
                        </Grid>

                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            color="primary"
                            className={classes.submit} onClick={event => {
                            buttonOnclickMethod(event);
                        }}
                        >
                            FIND
                        </Button>
                        <Grid item xs={12} sm={20}>
                            <div className = "md:pl-4">
                                {patientExist &&
                                    <p className={classes.form}>Patient Id : {patient.id}</p>
                                }
                                {patientExist &&
                                    <p className={classes.form}>Name : {patient.name}</p>
                                }
                                {patientExist &&
                                    <p className={classes.form}>Age : {patient.age}</p>
                                }
                                {patientExist &&
                                    <p className={classes.form}>Gender : {patient.gender}</p>
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
function getPatient(pdata) {
    console.log("Get Patient by Id : ",pdata.id);
    return fetch(backendUrl+'patients/patient/patientId/'+pdata.id)
        .then(response => response.json())
        .then(data => {
            if(data !=null  ){
                console.log(data.id);
                return data;

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

export default Profile;