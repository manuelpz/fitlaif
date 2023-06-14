import { useRouter } from "next/router";
import { useState, useEffect } from "react";
import Headers from "../../../components/Headers";
import Image from "next/image";
import Confetti from 'react-confetti';
import ReactModal from 'react-modal';
import estilos from '../../../components/Modal.module.css';
import CartaMusculo from "../../../components/CartaMusculo";

export default function Rutina() {
  const [ejercicio, setEjercicio] = useState([]);
  const router = useRouter();
  const [misEjercicios, setMisEjercicios] = useState([]);
  const [enable, setEnable] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(true);
  const { musculo } = router.query;

  //FUNCIONES
  const reload = () => {
    window.location.reload();
  }

  const irAEntrenamientos = () => {
    router.push("/entrenamientos");
  }

  const seleccionarEjercicio = (ejercicioSeleccionado) => {
    if (!misEjercicios.includes(ejercicioSeleccionado)) {
      setMisEjercicios([...misEjercicios, ejercicioSeleccionado])
    }
    else {
      setMisEjercicios(misEjercicios.filter(ejercicio => ejercicio !== ejercicioSeleccionado))
    }
  }

  const estaSeleccionado = (ejercicio) => {
    if (misEjercicios.includes(ejercicio)) {
      return '!bg-red-500'
    }
  }

  //Setea a completado un ejercicio cuando se pulsa el boton
  const handleCompletado = (index) => {
    setMisEjercicios((prevMisEjercicios) => {
      const updatedMisEjercicios = [...prevMisEjercicios];
      updatedMisEjercicios[index].completado = true;
      return updatedMisEjercicios;
    })
  }

  useEffect(() => {
    const fetchData = async () => {
      const response = await fetch("http://localhost:8080/ejercicios");
      const data = await response.json();
      setEjercicio(data.filter((e) => e.musculo.toLowerCase() === musculo.toLowerCase()));
    }
    if (musculo) {
      fetchData();
    }
  }, [musculo]);

  //PÁGINA QUE MUESTRO MIENTRAS CARGA LOS EJERCICIOS
  if (ejercicio.length === 0) {
    return (
      <div>
        <Headers title="Entreamiento | FitLaif" />
        <h1>Cargando ejercicios...</h1>
      </div>
    )
  }

  //RENDERIZADO NORMAL ELIGIENDO EJERCICIOS
  if (enable === false) {
    return (
      <div className="container mx-auto">
        <Headers title={"Entreamiento | FitLaif"} description={"Entrenamiento personalizado | FitLaif"} />
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4 justify-items-center">
          {ejercicio.map((e) => (
            <div onClick={() => seleccionarEjercicio(e)} key={e.ejercicioId} className={`max-w-md w-full rounded overflow-hidden shadow-lg bg-gray-900 text-white ${estaSeleccionado(e)}`}>
              <div className="p-4">
                <div className="font-bold text-center">{e.ejercicio.toUpperCase()}</div>
                {e.img ? (
                  <div className="mt-4">
                    <Image className="w-full h-auto" src={e.img} width={300} height={300} alt="Imagen explicativa del ejercicio" />
                  </div>
                ) : (
                  ""
                )}
              </div>
            </div>
          ))}
        </div><br />
        <button type="button" onClick={() => setEnable(true)}>Comenzar</button>
      </div>
    );
  }

  //RENDERIZADO NORMAL CON LOS EJERCICIOS YA ELEGIDOS
  if (misEjercicios.length > 0 && enable === true) {
    const todosCompletados = misEjercicios.every((e) => e.completado)
    return (
      <div className="container mx-auto">
        <Headers title={"Entrenamiento | FitLaif"} description={"Entrenamiento personalizado | FitLaif"} />
        <div className="flex flex-col items-center">
          <CartaMusculo ejerciciosElegidos={misEjercicios}/>
          {todosCompletados && (
            <Confetti width={window.innerWidth} height={window.innerHeight} />
          )}
          {todosCompletados && (<ReactModal
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
            <h3 className={`${estilos.entreno} text-center`}>¡ENHORABUENA! Has completado el entrenamiento de hoy</h3>
            <div className='grid grid-cols-2 gap-4 content-center'>
              <button type="button" className="text-white bg-green-700 hover:bg-green-800 focus:outline-none focus:ring-4 focus:ring-green-300 font-medium rounded-full text-sm px-5 py-2.5 text-center mr-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800" onClick={irAEntrenamientos}>Volver</button>
              <button type="button" className="text-white bg-red-700 hover:bg-red-800 focus:outline-none focus:ring-4 focus:ring-red-300 font-medium rounded-full text-sm px-5 py-2.5 text-center mr-2 mb-2 dark:bg-red-600 dark:hover:bg-red-700 dark:focus:ring-red-900" onClick={() => setIsModalOpen(false)}>Quedarme aquí</button>
            </div>
          </ReactModal>)}
        </div>
      </div>
    );
  }


  //SE PULSA EL BOTON SIN AÑADIR EJERCICIOS
  if (misEjercicios.length === 0 && enable === true) {
    return (
      <div>
        <h1>Añade ejercicios tio</h1>
        <button type="button" onClick={reload}>Volver</button>
      </div>
    )
  }
}
