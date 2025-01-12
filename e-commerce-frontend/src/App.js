import './App.css';
import LandingPage from './components/pages/LandingPage';
import SignInPage from './components/pages/LoginPage';
import SignUpPage from './components/pages/RegisterPage';
import ForgetPasswordPage from './components/pages/ForgetPasswordPage';
import HomePage from './components/pages/HomePage';
import {BrowserRouter, Routes, Route} from "react-router-dom"



function App() {
  return (
    <BrowserRouter>
            <div>
                <Routes>
                    <Route exact path="/" element={ <LandingPage/> } />
                    <Route path="/login" element={ <SignInPage/> } />
                    <Route path="/register" element={ <SignUpPage/> } />
                    <Route path="/forget-password" element={ <ForgetPasswordPage/> } />
                    <Route path="/home" element={ <HomePage/> } />
                </Routes>    
            </div>
        </BrowserRouter>
  );
}

export default App;
