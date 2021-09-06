const errorsList = [
    {errorMessage: 'username not available', feedbackMessage: 'Nome de usuário não disponível'},
]

const serverError = 'Erro interno no servidor. Recarregue a página e tente novamente.'

export function generalExceptionTreatment(error){
    let err = errorsList.find(e => error.error.errors.includes(e.errorMessage))
    if (err){
        return err.feedbackMessage
    }
    return serverError
}
