import Image from "next/image";
import { useState } from "react";

export default function CartaMusculo({ ejerciciosElegidos }) {
    const [visibleDiv, setVisibleDiv] = useState(0);

    const handleClickDerecha = () => {
        setVisibleDiv((prevVisibleDiv) => (prevVisibleDiv + 1) % ejerciciosElegidos.length);
    }

    const handleClickIzquierda = () => {
        if (visibleDiv === 0) {
            setVisibleDiv(ejerciciosElegidos.length - 1);
        }
        else {
            setVisibleDiv((prevVisibleDiv) => (prevVisibleDiv - 1) % ejerciciosElegidos.length)
        }
    }

    return (
        <div>
            <div>
                {ejerciciosElegidos.map((e, index) => (
                    <div
                        key={e.ejercicioId}
                        className={`max-w-sm w-full lg:max-w-full justify-center flex ${index === visibleDiv ? "z-10" : "hidden"
                            }`}
                    >
                        <div className="border-r border-b border-l border-gray-400 lg:border-l-0 lg:border-t lg:border-gray-400 bg-white rounded-b lg:rounded-b-none lg:rounded-r p-4 justify-self-center leading-normal h-96 w-96 absolute transform -translate-x-1/2 w-32 h-32">
                            <div className="mb-8">
                                <div className="text-gray-900 font-bold text-xl mb-2 text-center">
                                    {e.ejercicio}
                                </div>
                                <p className="text-gray-700 text-base">Series: {e.series}</p>
                                <p className="text-gray-700 text-base">
                                    Repeticiones: {e.repeticiones}
                                </p>
                            </div>
                            <div className="flex items-center">
                                <Image src={'/iconos/flecha-izquierda.png'}
                                    width={50}
                                    height={50}
                                    alt="flecha hacia la izquierda"
                                    onClick={handleClickIzquierda} />
                                <Image
                                    src="/ejercicios/biceps/curl_biceps_mancuerna.webp"
                                    width={200}
                                    height={200}
                                    alt="Imagen explicativa del ejercicio"
                                />
                                <Image src={'/iconos/flecha-derecha.png'}
                                    width={50}
                                    height={50}
                                    alt="flecha hacia la derecha"
                                    onClick={handleClickDerecha} />
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}
