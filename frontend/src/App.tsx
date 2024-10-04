import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import ListarPessoas from './components/ListarPessoas';
import CadastroPessoa from './components/CadastroPessoa';

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<ListarPessoas />} />
                <Route path="/cadastro" element={<CadastroPessoa />} />
                <Route path="/cadastro/:cpf" element={<CadastroPessoa />} />
            </Routes>
        </Router>
    );
}

export default App;
