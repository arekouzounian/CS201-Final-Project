import { styled } from '@mui/material/styles';
import { AppBar } from '@mui/material';

export const StyledAppBar = styled(AppBar)(({ theme }) => ({
    borderRadius: 15,
    margin: '0px',
    display: 'flex',
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    padding: '0px',
    [theme.breakpoints.down('sm')]: {
      flexDirection: 'column',
    },
    width: '1335px',
    height: '50px',
}));


export const StyledButton = styled('button')(({theme}) => ({
    margin:'20px',
    height:'50px',
}));





