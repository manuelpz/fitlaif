import Link from "next/link";
import Image from "next/image";
import { useState } from "react";
import { useRouter } from "next/router";

const Navbar = () => {
    const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);

    const toggleMobileMenu = () => {
        setIsMobileMenuOpen(!isMobileMenuOpen);
    }

    const router = useRouter()


    return (
        <nav className="bg-gray">
            <div className="mx-auto sm:px-6 lg:px-8">
                <div className="flex items-center justify-between h-16">
                    <div className="flex items-center">
                        <div className="flex-shrink-0 justify-start">
                            <Link href="/">
                                <Image src={'/logo/logo_transparente.png'} width={100} height={100} alt="FitLaif Logo" />
                            </Link>
                        </div>
                    </div>
                    <div className="flex justify-end">
                        <div className="hidden md:block space-x-20">
                            <Link className={router.pathname == '/' ? 'active' : 'nonActive'} href="/">
                                Inicio
                            </Link>
                            <Link className={router.pathname == '/entrenamientos' ? 'active' : 'nonActive'} href="/entrenamientos">
                                Entrenamientos
                            </Link>
                            <Link className={router.pathname == '/peso' ? 'active' : 'nonActive'} href="/peso">
                                Peso
                            </Link>
                            <Link className={router.pathname == '/contacto' ? 'active' : 'nonActive'}  href="/contacto">
                                Contacto
                            </Link>
                        </div>
                        <div className="md:hidden">
                            <button
                                onClick={toggleMobileMenu}
                                className="text-gray-300 hover:text-white focus:outline-none focus:text-white"
                                aria-label="Toggle menu"
                            >
                                <svg className="h-6 w-6 fill-current" viewBox="0 0 24 24">
                                    {isMobileMenuOpen ? (
                                        <path
                                            fillRule="evenodd"
                                            clipRule="evenodd"
                                            d="M19 11H5v-1h14v1zm0-5H5V6h14v1zm0 10H5v-1h14v1z"
                                        />
                                    ) : (
                                        <path
                                            fillRule="evenodd"
                                            clipRule="evenodd"
                                            d="M4 6h16v1H4V6zm0 5h16v1H4v-1zm0 5h16v1H4v-1z"
                                        />
                                    )}
                                </svg>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            {isMobileMenuOpen && (
                <div className="md:hidden">
                    <ul className="px-2 pt-2 pb-3 space-y-1 sm:px-3 text-right">
                        <li className="block">
                            <Link className='text-white hover:bg-white hover:text-black items-center'  href="/">
                            Inicio
                        </Link>
                        </li>
                        <li className="block">
                            <Link className='text-white hover:bg-white hover:text-black items-center' href="/entrenamientos">
                            Entrenamientos
                        </Link>
                        </li>
                        <li className="block">
                            <Link className='text-white hover:bg-white hover:text-black items-center' href="/peso">
                            Peso
                        </Link>
                        </li>
                        <li className="block">
                            <Link className='text-white hover:bg-white hover:text-black items-center' href="/contacto">
                            Contacto
                        </Link>
                        </li>
                    </ul>
                </div>
            )}
        </nav>
    );
};

export default Navbar;

