# 🐎 API Venda de Cavalos

Este é um projeto de API REST desenvolvida com Spring Boot, voltada para o gerenciamento de anúncios e vendas de cavalos, especialmente da raça Mangalarga. 
A aplicação permite que criadores e vendedores cadastrem animais, simulem vendas, consultem documentos oficiais e entrem em contato direto via WhatsApp.

---

## 🚀 Funcionalidades

- Cadastro de clientes e cavalos
- Consulta de cavalos disponíveis para venda
- Consulta automática de documentos no site oficial da raça
- Simulação de venda com cálculo de comissão
- Geração de link direto para contato via WhatsApp
- Estrutura pronta para integração com pagamentos e contrato digital

---

## 🔧 Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Jsoup (web scraping)
- H2 (para testes locais) ou MySQL
- Maven

---

## 📂 Endpoints principais

### Clientes
- `POST /clientes/cadastrar` → Cadastra um cliente

### Cavalos
- `POST /cavalos/cadastrar` → Cadastra um cavalo
- `GET /cavalos/disponiveis` → Lista cavalos ainda não vendidos
- `POST /cavalos/vender/{id}` → Consulta documento detalhado do cavalo
- `POST /cavalos/comprar/{id}?metodoPagamento=PIX` → Realiza a venda

### Contato via WhatsApp
- `GET /contato/whatsapp?telefone=XXX&mensagem=...` → Gera link direto para abrir no WhatsApp

---

## 📄 Consulta de documento

A API se conecta diretamente ao site [cavalomangalarga.com.br](https://cavalomangalarga.com.br) para consultar dados detalhados de cada cavalo a partir do número de documento fornecido no cadastro.

---

## 📦 Como rodar o projeto

```bash
git clone https://github.com/Carloseman1/ApiVendaCavalos.git
cd ApiVendaCavalos

# Para rodar localmente
./mvnw spring-boot:run
