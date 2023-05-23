import Image from 'next/image'
import Link from 'next/link'
import { useState } from 'react'
import ReactModal from 'react-modal';
import Headers from '../components/Headers';
import { useFetch } from '../funciones/useFetch';
import estilos from '../components/Modal.module.css';

export default function Entrenamientos() {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const MENSAJE_DE_ERROR = 'Algo no ha ido bien y no hemos podido recuperar los entrenamientos... Por favor, contacta con un administrador'

  const eliminarEntrenamiento = async (id) => {
    fetch(`http://localhost:8080/entrenamientos/eliminar/${id}`, {
      method: 'DELETE'
    })
    location.reload()
  }

  const calcularClassName = (prioridad) => {
    if (prioridad == 'Alta') {
      return 'bg-red-500 '
    }
    if (prioridad == 'Media') {
      return 'bg-yellow-500'
    }
    if (prioridad == 'Baja') {
      return 'bg-green-500'
    }
  }

  const {data, loading, error} = useFetch('http://localhost:8080/entrenamientos')


  if (loading) {
    return (
      <>
        <Headers title={'Entrenamientos | FitLaif'} description={'Guarda, selecciona, borra, o edita tus entrenamientos'}></Headers>
        <div className="spinnerContainer">
          <div className="spinner"></div>
          <div className="loader">
            <p>Cargando</p>
            <div className="words">
              <span className="word">entrenamientos</span>
              <span className="word">pilas</span>
              <span className="word">energia</span>
            </div>
          </div>
        </div>
      </>
    )
  }

  if (error) {
    return (
      <>
        <Headers title={'Entrenamientos | FitLaif'} description={'Guarda, selecciona, borra, o edita tus entrenamientos'}></Headers>
        {MENSAJE_DE_ERROR}
      </>
    )
  }

  return (
    <div className="container mx-auto">
      <Headers title='Entrenamientos | Fitlaif' description='Guarda, selecciona, borra, o edita tus entrenamientos'></Headers>
      <h1 className='text-center'>¿Qué vamos a entrenar hoy?</h1>
      <h3 className='text-center'>En esta sección podrás elegir uno de los siguientes músculo y nosotros te haremos una rutina compuesta de 4 ejercicios, o tú mismo elegirás los ejercicios y registrarás tus PR!🏋🏻‍♀️</h3>
      <div className="grid grid-cols-3 gap-4 ">
        {data?.map((e) => (
          <div className="max-w-md mx-auto rounded overflow-hidden shadow-lg hover:shadow-xl transition duration-500 !bg-gray-900" key={e.entrenamientoId} suppressHydrationWarning={true}>
            <div className="relative h-auto">
              <Link
                href={`/entrenamientos/${e.entrenamientoId}`}
                key={e.entrenamientoId}
              >
                <div>
                  <Image
                    className="w-full h-auto"
                    width={400}
                    height={400}
                    src={e.img}
                    alt="Entrenamiento"
                  />
                </div>
                <div className="px-6 py-4">
                  <div className="font-bold text-xl mb-2 text-center">{e.musculo}</div>
                  <p className="text-white text-center">Breve descripcion</p>
                </div>
              </Link>
            </div>
            <div className="text-white text-center">
              Prioridad / Frecuencia 
              <div className=" relative px-6 pt-4 pb-2">
                <span className={'inline-block rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2 text-white '+calcularClassName(e.prioridad)}>
                  <b>{e.prioridad}</b>
                </span>
              </div>
            </div>
            <ReactModal
              className={estilos.customModal}
              isOpen={isModalOpen}
              style={{
                overlay: {
                  backgroundColor: 'rgba(0, 0, 0, 0.5)'
                },
                content: {
                  width: '250px',
                  height: '200px',
                  margin: 'auto'
                }
              }} >
              <h3 className={`${estilos.entreno} text-center`}>¿Estás seguro que quieres eliminar este entrenamiento?</h3>
              <div className='grid grid-cols-2 gap-4 content-center'>
                <button type="button" className="text-white bg-green-700 hover:bg-green-800 focus:outline-none focus:ring-4 focus:ring-green-300 font-medium rounded-full text-sm px-5 py-2.5 text-center mr-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800" onClick={() => eliminarEntrenamiento(e.entrenamientoId)}>Eliminar</button>
                <button type="button" className="text-white bg-red-700 hover:bg-red-800 focus:outline-none focus:ring-4 focus:ring-red-300 font-medium rounded-full text-sm px-5 py-2.5 text-center mr-2 mb-2 dark:bg-red-600 dark:hover:bg-red-700 dark:focus:ring-red-900" onClick={() => setIsModalOpen(false)}>Cancelar</button>
              </div>
            </ReactModal>
          </div>

        ))}
      </div>
      <div>
      </div>
    </div>
  )
}
