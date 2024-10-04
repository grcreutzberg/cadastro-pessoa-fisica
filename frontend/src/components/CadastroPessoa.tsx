// @ts-nocheck
import React, { useState, useEffect } from 'react';
import { TextField, Button, Container, Alert } from '@mui/material';
import Grid from '@mui/material/Grid2';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import styled from 'styled-components';
import InputMask from 'react-input-mask';

const StyledContainer = styled(Container)`
  margin-top: 20px;
  padding: 20px;
  background-color: #f5f5f5;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
`;

const Title = styled.h1`
  text-align: center;
  color: #3f51b5;
  margin-bottom: 20px;
`;

const CadastroPessoa = () => {
  const { cpf } = useParams();
  const [formData, setFormData] = useState({
    cpf: '',
    nome: '',
    telefone: '',
    cep: '',
    estado: '',
    municipio: '',
    bairro: '',
    numero: '',
    logradouro: '',
    complemento: ''
  });

  const navigate = useNavigate();
  const [isEditing, setIsEditing] = useState(true);
  const [isCepLoading, setIsCepLoading] = useState(false);
  const [successMessage, setSuccessMessage] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  // Se houver CPF na URL, buscamos os dados da pessoa para exibição
  useEffect(() => {
    if (cpf) {
      axios.get(`http://localhost:8080/api/v1/pessoa-fisica/${cpf}`)
        .then(response => {
          setFormData(response.data.content);
          setIsEditing(false);  // Desabilitar edição
        })
        .catch(error => {
          console.error('Erro ao buscar pessoa:', error);
        });
    }
  }, [cpf]);

  // Função para buscar informações do CEP
  const fetchAddressByCep = async (cep: string) => {
    try {
      setIsCepLoading(true);
      const response = await axios.get(`https://viacep.com.br/ws/${cep}/json/`);
      const { bairro, localidade, estado, logradouro, complemento } = response.data;

      // Atualiza os campos com os dados retornados e bloqueia a edição
      setFormData((prevFormData) => ({
        ...prevFormData,
        bairro,
        municipio: localidade,
        estado,
        logradouro,
        complemento
      }));
    } catch (error) {
      console.error('Erro ao buscar o CEP:', error);
    } finally {
      setIsCepLoading(false);
    }
  };

  // Função para lidar com a mudança de valor nos campos
  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;

    if (name === 'cep' && value.length > 8) {
      return;
    }

    setFormData({
      ...formData,
      [name]: value
    });

    // Se o campo CEP for preenchido com 8 dígitos, dispara a busca
    if (name === 'cep' && value.length === 8) {
      setTimeout(() => {
        fetchAddressByCep(value);
      }, 500);
    }
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    // Enviar os dados para o backend
    axios.post('http://localhost:8080/api/v1/pessoa-fisica/', formData)
      .then(response => {
        if (response.status == 200) {
          setErrorMessage('');
          setSuccessMessage('Pessoa cadastrada com sucesso!');
          setIsEditing(false);
        }
      }).catch(error => {
        setSuccessMessage('');
        setErrorMessage(error.response.data.statusMessage);
        console.error('Erro ao cadastrar pessoa: ' + error.response.data);
      });
  };

  // Função para voltar para a tela principal
  const handleGoBack = () => {
    navigate(`/`);  // Redireciona para tela de listagem de pessoas
  };

  return (
    <StyledContainer>
      {successMessage && <Alert variant="filled" severity="success">{successMessage}</Alert>}
      {errorMessage && <Alert variant="filled" severity="error">{errorMessage}</Alert>}
      <Title>{cpf ? 'Visualizar Pessoa' : 'Cadastrar Pessoa'}</Title>
      <form onSubmit={handleSubmit}>
        <Grid container spacing={2}>
          <Grid size={4}>
            <InputMask
              mask="999.999.999-99"
              value={formData.cpf}
              onChange={handleChange}
              disabled={!isEditing}
            >
              {(inputProps: any) => <TextField {...inputProps} name="cpf" label="CPF" fullWidth required />}
            </InputMask>
          </Grid>
          <Grid size={4}>
            <TextField
              name="nome"
              label="Nome"
              fullWidth
              required
              value={formData.nome}
              onChange={handleChange}
              disabled={!isEditing}
            />
          </Grid>
          <Grid size={4}>
            <InputMask
              mask="(99) 99999-9999"
              value={formData.telefone}
              onChange={handleChange}
              disabled={!isEditing}
            >
              {(inputProps: any) => <TextField {...inputProps} name="telefone" label="Telefone" fullWidth required />}
            </InputMask>
          </Grid>
          <Grid size={12}>
            <TextField
              name="cep"
              label="CEP"
              fullWidth
              required
              value={formData.cep}
              onChange={handleChange}
              disabled={isCepLoading || !isEditing}
              helperText={isCepLoading ? "Buscando endereço..." : ""}
            />
          </Grid>
          <Grid size={6}>
            <TextField
              name="municipio"
              label="Município"
              fullWidth
              required
              value={formData.municipio}
              onChange={handleChange}
              disabled
            />
          </Grid>
          <Grid size={6}>
            <TextField
              name="estado"
              label="Estado"
              fullWidth
              required
              value={formData.estado}
              onChange={handleChange}
              disabled
            />
          </Grid>
          <Grid size={6}>
            <TextField
              name="bairro"
              label="Bairro"
              fullWidth
              required
              value={formData.bairro}
              onChange={handleChange}
              disabled
            />
          </Grid>
          <Grid size={6}>
            <TextField
              name="numero"
              label="Número"
              fullWidth
              required
              value={formData.numero}
              onChange={handleChange}
              disabled={!isEditing}
            />
          </Grid>
          <Grid size={6}>
            <TextField
              name="logradouro"
              label="Rua"
              fullWidth
              required
              value={formData.logradouro}
              onChange={handleChange}
              disabled
            />
          </Grid>
          <Grid size={6}>
            <TextField
              name="complemento"
              label="Complemento"
              fullWidth
              value={formData.complemento}
              onChange={handleChange}
              disabled={!isEditing}
            />
          </Grid>
          <Grid size={6}>
            <Button variant="contained" type="submit" fullWidth disabled={!isEditing} color='primary'>
              Salvar
            </Button>
          </Grid>
          <Grid size={6}>
            <Button variant="contained" type="button" onClick={handleGoBack} fullWidth color='secondary'>
              {isEditing ? 'Cancelar' : 'Fechar'}
            </Button>
          </Grid>
        </Grid>
      </form>
    </StyledContainer>
  );
};

export default CadastroPessoa;
