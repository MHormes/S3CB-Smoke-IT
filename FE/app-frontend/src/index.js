import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import MainPageContainer from './components/MainPageContainer';

ReactDOM.render(
  <React.StrictMode>
    <BrowserRouter>
    <MainPageContainer/>
    </BrowserRouter>
  </React.StrictMode>,
  document.getElementById('root')
);
