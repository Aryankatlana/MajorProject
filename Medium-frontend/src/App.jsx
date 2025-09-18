import React from 'react';
import './App.css';
import Footer from './Footer';
import Header from './Header';
import Mid from './Mid';

function App() {
  return (
    <div className="App">
     <Header className="header"/>
      <Mid className="main-content"/>
      <Footer className="footer"/>
    </div>
  );
}

export default App;