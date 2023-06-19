import '@/styles/globals.css';
import Head from 'next/head';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Navbar from '../components/Navbar';
import { UserContextProvider } from '../context/usuarioContext';
import { useEffect, useState } from 'react';
import Login from './login';
import Registro from './registro';
import Router from 'next/router';
import NoAutenticado from './noAutenticado';
export default function App({ Component, pageProps }) {
  const [isLogged, setIsLogged] = useState(false)
  const [routerPath, setRouterPath] = useState('')

  //RECUPERA EL ESTADO 'ISLOGGED' DE LOCAL STORAGE Y LO SETEA
  useEffect(() => {
    setIsLogged(window.localStorage.getItem('logged'))
    setRouterPath(Router.pathname)
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  //RENDEIZADO SI NO ESTA LOGEADO
  if ((!isLogged || isLogged === undefined || isLogged === null) && (routerPath === '/login' || routerPath === '/') )  {
    return (
      <div>
        <UserContextProvider>
          <Head>
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/dark.css" />
          </Head>
          <Login />
          </UserContextProvider>
      </div>
      )
  }

  if ((!isLogged || isLogged === undefined) && routerPath === '/registro') {
    return (
      <div>
        <UserContextProvider>
          <Head>
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/dark.css" />
          </Head>
          <Registro />
        </UserContextProvider>
      </div>
    )
  }
  //RENDEIZADO SI ESTA LOGEADO
  if (isLogged) {
    return (
      <div>
        <UserContextProvider>
          <Navbar></Navbar>
          <Head>
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/dark.css" />
          </Head>
          <Component {...pageProps} />
          <ToastContainer />
        </UserContextProvider>
      </div>
    )
  }
  else return (<div>
    <UserContextProvider>
      <Navbar></Navbar>
      <Head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/dark.css" />
      </Head>
      <NoAutenticado/>
      <ToastContainer />
    </UserContextProvider>
  </div>)
}
