import { useEffect, useState } from 'react'
import { toast } from "react-toastify";
import Link from 'next/link';
export default function GuardarEntrenamiento() {
    const [entrenamientoId, setEntrenamientoId] = useState('');
    const [musculo, setMusculo] = useState('');
    const [img, setImg] = useState('');
    const [hashtag, setHashtag] = useState([]);
    const [ultimoId, setUltimoId] = useState('')

    const fetchData = async () => {
        const response = await fetch('http://localhost:8080/entrenamientos')
        const data = await response.json()
        setUltimoId(data.length + 1)
    }
    useEffect(() => {
        fetchData()
        setEntrenamientoId(ultimoId)
        console.log(entrenamientoId)

    }, [entrenamientoId, ultimoId])

    const handleSubmit = async (event) => {
        event.preventDefault();

        // Crear objeto con los datos del formulario
        const data = {
            entrenamientoId,
            musculo,
            img,
            hashtag
        }

        try {
            if (musculo != null && musculo.trim().length) {
                if (hashtag != null && hashtag.length) {
                    const response = await fetch('http://localhost:8080/entrenamientos/guardar', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify(data),
                    })
                    setMusculo('');
                    setImg('');
                    setHashtag([]);

                    if (response.ok) {
                        toast.success('¡WOOOOOW! Tenemos otro músculo más, ¡a mutar!')
                        
                    } else {
                        toast.error('Parece que ya estamos trabajando ese músculo...');
                    }
                }
                else {
                    toast.error('¿Ni un hastag?¿Dónde quedó la motivación? ¡Añade al menos uno!')
                }
            }

            else {
                toast.error('Debes añadir al menos un músculo, ¿no crees?');
            }
        } catch (error) {
            toast.error('Error en el servidor, ponte en contacto con un administrador');
        }
    }
    return (
        <div>
            <form onSubmit={handleSubmit} className="w-full max-w-lg">
                <div className="flex flex-wrap -mx-3 mb-6">
                    <div className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
                        <label className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" htmlFor="grid-first-name">
                            MUSCULO
                        </label>
                        <input value={musculo} onChange={(e) => setMusculo(e.target.value)} className="appearance-none block w-full bg-gray-200 text-gray-700 border border-red-500 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white" id="grid-first-name" type="text" placeholder="Femoral" />
                        <p className="text-red-500 text-xs italic">Campo obligatorio</p>
                    </div>
                    <div className="w-full md:w-1/2 px-3">
                        <label className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" htmlFor="grid-last-name">
                            HASHTAG
                        </label>
                        <input value={hashtag.join(',')} onChange={(e) => setHashtag(e.target.value.split(',').map(String))} name="hashtag[]" multiple className="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" id="grid-last-name" type="text" placeholder="#gym" />
                    </div>
                </div>
                <button type="submit">Enviar</button>
                <Link href={'/entrenamientos'}>
                <button >Volver</button>
                </Link>
            </form>
        </div>
    )
}