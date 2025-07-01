package edu.uph.ayamsejahtera.ui.utama;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UtamaViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public UtamaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}