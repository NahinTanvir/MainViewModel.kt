private fun setupObserver() {
    lifecycleScope.launch {
        mainViewModel.getContinents().observe(this@MainActivity) {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.show()
                    binding.rvContinents.gone()
                }
                is Resource.Success -> {
                    binding.progressBar.gone()
                    binding.rvContinents.show()
                    //Update UI according to the data received
                }
                is Resource.Error -> {
                    binding.progressBar.gone()
                    binding.rvContinents.show()
                    showToast(it.message.toString())
                }
                else -> {
                    Log.d(TAG, "setupObserver: Else - Called")
                }
            }
        }

    }
}
