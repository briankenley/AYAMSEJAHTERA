package edu.uph.ayamsejahtera.ui.deteksisuhu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DeteksiSuhuViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DeteksiSuhuViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}