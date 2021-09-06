const errorsList = [
    {errorMessage: 'Username not available', feedbackMessage: 'Nome de usuário não disponível'},
    {errorMessage: 'Cpf not available', feedbackMessage: 'CPF já cadastrado'},
]

const serverError = 'Erro interno no servidor. Recarregue a página e tente novamente.'

export function generalExceptionTreatment(error){
    let err = errorsList.find(e => error.error.error.includes(e.errorMessage))
    if (err){
        return err.feedbackMessage
    }
    return serverError
}
