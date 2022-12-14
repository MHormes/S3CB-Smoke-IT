import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import MainContainer from './components/MainContainer';

ReactDOM.render(
  <React.StrictMode>
    <BrowserRouter>
    <MainContainer/>
    </BrowserRouter>
  </React.StrictMode>,
  document.getElementById('root')
);
