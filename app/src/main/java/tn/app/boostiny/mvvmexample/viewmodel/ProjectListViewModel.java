package tn.app.boostiny.mvvmexample.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import tn.app.boostiny.mvvmexample.model.Project;
import tn.app.boostiny.mvvmexample.repository.ProjectRepository;

public class ProjectListViewModel extends AndroidViewModel {


    private final LiveData<List<Project>> projectListObservable;
    private final static String TAG = "ProjectListViewModel" ;

    public ProjectListViewModel(@NonNull Application application) {
        super(application);
        projectListObservable = ProjectRepository.getInstance().getProjectList("arshavin2000");

    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    public LiveData<List<Project>> getProjectListObservable() {
        try {
            Log.e(TAG, "getProjectListObservable: " + projectListObservable.getValue().get(0) );
        }
        catch (NullPointerException ex){
            Log.e(TAG, "getProjectListObservable: " + ex );
        }
        return projectListObservable;
    }


}
