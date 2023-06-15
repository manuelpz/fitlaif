import { useState, useContext } from 'react';
import { UserContext } from '../../context/usuarioContext';
import Headers from '../../components/Headers';
import { useRouter } from 'next/router';
export default function Registro() {
    const router = useRouter()
    const [usuario, setUsuario] = useState('');
    const [password, setPassword] = useState('');
    const { setIsLogged } = useContext(UserContext);

    //SI EL USUARIO Y LA CONTRASEÑA COINCIDEN, ALMACENA LOS DATOS DEL USUARIO DE LOCAL STORAGE E INICIA SESION
    const logIn = async () => {
        const response = await fetch(`http://localhost:8080/usuarios/${usuario}/${password}`)
        if (response.status !== 409) {
            const data = await response.json()
            setIsLogged(data.isLogged)
            const loggin = window.localStorage;
            loggin.setItem('logged', data.isLogged);
            loggin.setItem('userName', data.usuario);
            router.push = ('/')
        }
        else {
            alert("Usuario inválido")
        }
    }

    const irALogin = () => {
        window.location.href = '/login'
    }

    //RENDERIZADO NORMAL ----->
    return (
        <div className="flex items-center justify-center min-h-screen bg-gray-900">
            <Headers title="Registro" description="Portal de registro de usuarios de la web de FitLaif" />
            <div className="max-w-md w-full bg-gray-800 rounded-lg overflow-hidden shadow-lg">
                <div className="px-6 py-8">
                    <h2 className="text-2xl font-semibold mb-6 text-white">Iniciar sesión</h2>
                    <div className="mb-4">
                        <label htmlFor="usuario" className="block text-white text-sm mb-2">
                            Usuario
                        </label>
                        <div className="relative">
                            <input
                                type="text"
                                name="usuario"
                                value={usuario}
                                onChange={(e) => setUsuario(e.target.value)}
                                className="w-full bg-gray-200 rounded-md py-2 px-3 text-gray-800 text-xs"
                            />
                        </div>
                    </div>
                    <div className="mb-6">
                        <label htmlFor="contraseña" className="block text-white text-sm mb-2">
                            Contraseña
                        </label>
                        <div className="relative">
                            <input
                                type="password"
                                name="contraseña"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                className="w-full bg-gray-200 rounded-md py-2 px-3 text-gray-800 text-xs"
                            />
                        </div>
                    </div>
                    <button
                        onClick={logIn}
                        className="bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600"
                    >
                        Entrar
                    </button>
                    <div className='text-center'>
                        <p className="text-white text-base text-center font-bold">¿Ya tienes cuenta?</p>
                        <button
                            onClick={irALogin}
                            className="bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600"
                        >
                            Accede!
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}
