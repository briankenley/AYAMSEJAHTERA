package edu.uph.ayamsejahtera.ui.akun;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AkunViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AkunViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}