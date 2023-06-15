import '@/styles/globals.css';
import Head from 'next/head';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Navbar from '../components/Navbar';
import { UserContextProvider } from '../context/usuarioContext';
import { useEffect, useState } from 'react';
import Login from './login';

export default function App({ Component, pageProps }) {
  const [isLogged, setIsLogged] = useState(false)


  useEffect(() => {
    setIsLogged(window.localStorage.getItem('logged'));

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  if (!isLogged || isLogged === undefined)  {
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
    );
  } else {
    return (
      <div>
        <UserContextProvider>
          <Head>
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/dark.css" />
          </Head>
          <Component {...pageProps} />
          <ToastContainer />
        </UserContextProvider>
      </div>
    );
  }
}
