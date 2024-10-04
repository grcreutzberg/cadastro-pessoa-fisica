// @ts-nocheck
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { Button, Container, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Stack, IconButton } from '@mui/material';
import Grid from '@mui/material/Grid2';
import AddIcon from '@mui/icons-material/Add';
import FileDownloadIcon from '@mui/icons-material/FileDownload';
import SearchIcon from '@mui/icons-material/Search';
import DeleteIcon from '@mui/icons-material/Delete';

interface Pessoa {
  cpf: string;
  nome: string;
  telefone: string;
  cep: string;
  estado: string;
  municipio: string;
  bairro: string;
  numero: string;
  logradouro: string;
  complemento: string;
}

const ListarPessoas: React.FC = () => {
  const [pessoas, setPessoas] = useState<Pessoa[]>([]);
  const navigate = useNavigate();

  // Função para buscar pessoas da API
  const fetchPessoas = () => {
    axios.get<Pessoa[]>('http://localhost:8080/api/v1/pessoa-fisica/')
      .then(response => {
        setPessoas(response.data.content);
      })
      .catch(error => {
        console.error('Erro ao buscar pessoas:', error);
      });
  };

  useEffect(() => {
    fetchPessoas();
  }, []);

  // Função para exportar CSV
  const exportToCSV = () => {
    axios.get(`http://localhost:8080/api/v1/pessoa-fisica/exportCSV`, { responseType: 'blob' })
      .then((response) => {
        const reader = new FileReader();
        reader.onload = () => {
          const csvContent = '\uFEFF' + reader.result;
          const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
          const url = window.URL.createObjectURL(blob);
          const link = document.createElement('a');
          link.href = url;
          link.setAttribute('download', 'pessoas.csv');
          document.body.appendChild(link);
          link.click();
          document.body.removeChild(link);
        };
        reader.readAsText(response.data);
      })
      .catch((error) => {
        console.error("Erro ao baixar o arquivo CSV: ", error);
      });
  };

  // Função para redirecionar ao formulário de cadastro
  const handleAddNew = () => {
    navigate('/cadastro');  // Redireciona para o formulário de cadastro
  };

  // Função para visualizar uma pessoa
  const handleView = (cpf: string) => {
    navigate(`/cadastro/${cpf}`);  // Redireciona para o formulário com os dados da pessoa
  };

  // Função para deletar uma pessoa
  const handleDelete = (cpf: string) => {
    axios.delete(`http://localhost:8080/api/v1/pessoa-fisica/${cpf}`)
      .then(() => {
        fetchPessoas();
      })
      .catch(error => {
        console.error('Erro ao deletar pessoa:', error);
      });
  };

  return (
    <Container>
      <Grid container justifyContent="center" alignItems="flex-start" style={{ minHeight: '100vh', marginTop: '30px' }}>
        <Grid size={12}>
          <Paper elevation={3} style={{ padding: '20px' }}>
            <Stack direction="row" justifyContent="space-between" alignItems="center" mb={2}>
              <Button variant="contained" color="primary" startIcon={<AddIcon />} onClick={handleAddNew}>
                NOVO
              </Button>
              <Button variant="contained" color="secondary" startIcon={<FileDownloadIcon />} onClick={exportToCSV}>
                CSV
              </Button>
            </Stack>

            <TableContainer component={Paper} style={{ marginTop: '20px' }}>
              <Table sx={{ minWidth: 800 }} aria-label="simple table">
                <TableHead>
                  <TableRow>
                    <TableCell>CPF</TableCell>
                    <TableCell>Nome</TableCell>
                    <TableCell>Telefone</TableCell>
                    <TableCell></TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {pessoas.map((pessoa) => (
                    <TableRow key={pessoa.cpf}>
                      <TableCell>{pessoa.cpf}</TableCell>
                      <TableCell>{pessoa.nome}</TableCell>
                      <TableCell>{pessoa.telefone}</TableCell>
                      <TableCell>
                        <Stack direction="row-reverse" spacing={1}>
                          <IconButton aria-label="delete" onClick={() => handleDelete(pessoa.cpf)}><DeleteIcon /></IconButton>
                          <IconButton aria-label="open" onClick={() => handleView(pessoa.cpf)}><SearchIcon /></IconButton>
                        </Stack>
                      </TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          </Paper>
        </Grid>
      </Grid>
    </Container>
  );
};

export default ListarPessoas;
