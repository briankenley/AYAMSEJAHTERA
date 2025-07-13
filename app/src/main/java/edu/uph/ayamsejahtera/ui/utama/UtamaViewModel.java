package edu.uph.ayamsejahtera.ui.utama;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UtamaViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public UtamaViewModel() {
        mText = new MutableLiveData<>();

    }
    public void setGreeting(String username) {
        mText.setValue("Hai, " + username);
    }

    // Metode ini sekarang akan mengembalikan sapaan dinamis
    public LiveData<String> getText() {
        return mText;
    }
}