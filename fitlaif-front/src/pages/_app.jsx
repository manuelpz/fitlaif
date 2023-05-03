import '@/styles/globals.css'
import Head from 'next/head'
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
export default function App({ Component, pageProps }) {
  return (
    <div>
      <Head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/dark.css"></link>
      </Head>
      <Component {...pageProps} />
      <ToastContainer />
    </div>
  )
}
