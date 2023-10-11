HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    fun getContinents() = liveData(Dispatchers.IO) {
        emit(Resource.Loading(null))
        try {
            val apiResponse = mainRepository.getContinents()
            if (!apiResponse.hasErrors()) {
                emit(Resource.Success(apiResponse.data?.continents))
            } else {
                emit(Resource.Error(null, apiResponse.errors.toString()))
            }
        } catch (exception: Exception) {
            emit(exception.localizedMessage?.let { Resource.Error(null, it) })
        }
    }

    fun getCountriesOfSelectedContinent(continentCode: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading(null))
        try {
            val apiResponse = mainRepository.getCountriesOfSelectedContinent(continentCode)
            if (!apiResponse.hasErrors()) {
                emit(Resource.Success(apiResponse.data?.continent?.countries))
            } else {
                emit(Resource.Error(null, apiResponse.errors.toString()))
            }
        } catch (exception: Exception) {
            emit(exception.localizedMessage?.let { Resource.Error(null, it) })
        }
    }

}
