import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'

import '../../App.css'
import makeApi, { URLS } from '../../utils/apiURLS'

const SignInPage=()=> {
    const navigate =  useNavigate()
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const handleLogin = async(event)=>{
        event.preventDefault();
        console.log("handleLogin is invoked")
        const requestBody={
            username:username,
            password:password
        }
        const config = {
            url : `${URLS.signIn}`,
            method:"POST",
            data:requestBody
          }
          const res = await makeApi(config)
          console.log(res)
          if(+res.status===200){
            navigate("/home");
          }
          
    }
    return (
        <div className="text-center m-5-auto">
            <h2>Sign in to us</h2>
            <div >
                <p>
                    <label>Username or email address</label><br/>
                    <input type="text" name="first_name" required onChange={(e)=>setUsername(e.target.value)}/>
                </p>
                <p>
                    <label>Password</label>
                    <Link to="/forget-password"><label className="right-label">Forget password?</label></Link>
                    <br/>
                    <input type="password" name="password" required onChange={(e)=>setPassword(e.target.value)}/>
                </p>
                <p>
                    <button id="sub_btn" type="submit" onClick={handleLogin}>Login</button>
                </p>
            </div>
            <footer>
                <p>First time? <Link to="/register">Create an account</Link>.</p>
                <p><Link to="/">Back to Homepage</Link>.</p>
            </footer>
        </div>
    )
}
export default SignInPage;