import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import 'semantic-ui-css/semantic.min.css';


import reportWebVitals from './reportWebVitals';

import Navbar from './Component/NavBar/Navbar';
import { BrowserRouter} from 'react-router-dom';
import JRoutes from './JRoutes';



const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
{/* <App/> */}

<Navbar/>
<JRoutes/>


</BrowserRouter>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
