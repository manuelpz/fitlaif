import Image from "next/image";
import { useState, useRef } from "react";
import { useRouter } from "next/router";
import Confetti from 'react-confetti';
import ReactModal from 'react-modal';
import estilos from '../components/Modal.module.css';


export default function CartaMusculo({ ejerciciosElegidos }) {
    const [visibleDiv, setVisibleDiv] = useState(0);
    const [completado, setCompletado] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(true)
    const touchStartRef = useRef(null);
    const router = useRouter();

    const irAEntrenamientos = () => {
        router.push("/entrenamientos");
    }

    //MANJEAR EL TOQUE DE MOVIL (ARRASTRAR DEDO)
    const handleTouchStart = (event) => {
        touchStartRef.current = {
            x: event.touches[0].clientX,
            y: event.touches[0].clientY
        };
    }

    const handleTouchEnd = (event) => {
        const touchEnd = {
            x: event.changedTouches[0].clientX,
            y: event.changedTouches[0].clientY
        };

        const touchDiffX = touchStartRef.current.x - touchEnd.x;
        const touchDiffY = touchStartRef.current.y - touchEnd.y;

        if (Math.abs(touchDiffX) > Math.abs(touchDiffY)) {
            // Movimiento horizontal
            if (touchDiffX > 0) {
                // Deslizamiento hacia la izquierda
                handleClickDerecha();
            } else if (touchDiffX < 0) {
                // Deslizamiento hacia la derecha
                handleClickIzquierda();
            }
        }
    }

    //MANJEA QUE SE VEA EL SIGUIENTE EJERCICIO
    const handleClickDerecha = () => {
        setVisibleDiv((prevVisibleDiv) => (prevVisibleDiv + 1) % ejerciciosElegidos.length);
    }

    //MANJEA QUE SE VEA EL EJERCICIO ANTERIOR
    const handleClickIzquierda = () => {
        if (visibleDiv === 0) {
            setVisibleDiv(ejerciciosElegidos.length - 1);
        }
        else {
            setVisibleDiv((prevVisibleDiv) => (prevVisibleDiv - 1) % ejerciciosElegidos.length)
        }
    }

    //AÑADE UN ELEMENTO A UN ARRAY DE EJERCICIOS COMPLETADO
    const agregarElemento = () => {
        const nuevoElemento = ejerciciosElegidos[visibleDiv]
        setCompletado([...completado, nuevoElemento])
    }

    //FUNCION QUE TACHA EL EJERCICIO
    const completarCarta = () => {
        if (completado.includes(ejerciciosElegidos[visibleDiv])) {
            return 'line-through'
        }
    }

    const estaCompletado = () => {
        if (completado.includes(ejerciciosElegidos[visibleDiv])) {
            return true
        }
    }

    //RENDERIZADO NORMAL
    return (
        <div className="container mx-auto">
            {ejerciciosElegidos.map((e, index) => (
                <div
                    key={e.ejercicioId}
                    className={`justify-center flex ${index === visibleDiv ? "z-10" : "hidden"}`}
                    onTouchStart={handleTouchStart}
                    onTouchEnd={handleTouchEnd}
                >
                    <div className="border-r border-b border-l border-gray-400 lg:border-l-0 lg:border-t lg:border-gray-400 bg-white rounded-b lg:rounded-b-none lg:rounded-r p-4 justify-self-center h-100 w-80 absolute">
                        <div className={`text-gray-900 font-bold text-xl mb-2 text-center ${completarCarta()}`}>
                            {e.ejercicio.toUpperCase()}
                        </div>
                        {e.img ? (<div className="grid justify-items-center !w-72 !h-48">
                            <Image className="w-full h-full" src={e.img} width={300} height={300} alt="Imagen explicativa del ejercicio" />
                        </div>) : ''}
                        <div className="mb-8">
                            <p className={`text-gray-700 text-base ${completarCarta()}`}>Series: {e.series}</p>
                            <p className={`text-gray-700 text-base ${completarCarta()}`}>Repeticiones: {e.repeticiones}</p>
                        </div>
                        <div className="grid justify-items-center">
                            <p className="text-gray-700 text-center">{e.descripcion}</p>
                            <button className="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded-fulld" onClick={agregarElemento} disabled={estaCompletado()}>
                                Completado!
                            </button>
                        </div>
                        <div className="grid grid-cols-3 justify-items-center">
                            <Image src="/iconos/flecha-izquierda.png" width={50} height={50} alt="flecha hacia la izquierda" onClick={handleClickIzquierda} />
                            <p className="text-gray-700 text-base text-center font-bold">{index + 1}/{ejerciciosElegidos.length}</p>
                            <Image src="/iconos/flecha-derecha.png" width={50} height={50} alt="flecha hacia la derecha" onClick={handleClickDerecha} />
                        </div>
                    </div>
                </div>
            ))}
            {completado.length === ejerciciosElegidos.length && <Confetti width={window.innerWidth} height={window.innerHeight} />}
            {completado.length === ejerciciosElegidos.length && (<ReactModal
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
    )
}
