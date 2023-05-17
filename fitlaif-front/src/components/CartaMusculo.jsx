import Image from "next/image";
import { useState, useRef } from "react";


export default function CartaMusculo({ ejerciciosElegidos }) {
    const [visibleDiv, setVisibleDiv] = useState(0);
    const [completado, setCompletado] = useState([]);
    const touchStartRef = useRef(null);

    //MANJEAR EL TOQUE DE MOVIL (ARRASTRAR DEDO)
    const handleTouchStart = (event) => {
        touchStartRef.current = event.touches[0].clientX;
    }

    const handleTouchEnd = (event) => {
        const touchEnd = event.changedTouches[0].clientX;
        const touchDiff = touchStartRef.current - touchEnd;

        if (touchDiff > 0) {
            // Deslizamiento hacia la izquierda
            handleClickDerecha();
        } else if (touchDiff < 0) {
            // Deslizamiento hacia la derecha
            handleClickIzquierda();
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
        const nuevoElemento = ejerciciosElegidos[visibleDiv];
        setCompletado([...completado, nuevoElemento]);
    }

    //FUNCION QUE TACHA EL EJERCICIO
    const completarCarta = () => {
        if (completado.includes(ejerciciosElegidos[visibleDiv])) {
            return 'line-through'
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
                    <div className="border-r border-b border-l border-gray-400 lg:border-l-0 lg:border-t lg:border-gray-400 bg-white rounded-b lg:rounded-b-none lg:rounded-r p-4 justify-self-center leading-normal lg:h-max lg:w-max h-100 w-80 absolute transform -translate-x-1/2">
                        <div className={`text-gray-900 font-bold text-xl mb-2 text-center ${completarCarta()}`}>
                            {e.ejercicio.toUpperCase()}
                        </div>
                        <div className="grid justify-items-center">
                            <Image className="center" src={e.img} width={300} height={300} alt="Imagen explicativa del ejercicio" />
                        </div>
                        <div className="mb-8">
                            <p className={`text-gray-700 text-base ${completarCarta()}`}>Series: {e.series}</p>
                            <p className={`text-gray-700 text-base ${completarCarta()}`}>Repeticiones: {e.repeticiones}</p>
                        </div>
                        <div className="grid justify-items-center">
                            <button className="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded-fulld" onClick={agregarElemento}>
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
        </div>
    );
}
