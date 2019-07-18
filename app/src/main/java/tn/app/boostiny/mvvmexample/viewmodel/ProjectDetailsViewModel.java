package tn.app.boostiny.mvvmexample.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import tn.app.boostiny.mvvmexample.model.Project;
import tn.app.boostiny.mvvmexample.repository.ProjectRepository;

public class ProjectDetailsViewModel extends AndroidViewModel {

    final private LiveData<Project> projectDetailsObservable ;
    public ObservableField<Project> project = new ObservableField<>();


    private ProjectDetailsViewModel(@NonNull Application application , @NonNull String projectId) {
        super(application);
        projectDetailsObservable = ProjectRepository.getInstance().getProjectDetails("arshavin2000",projectId);
    }


    public LiveData<Project>  getProjectDetailsObservable()
    {
        return projectDetailsObservable;
    }

    public void setProject(Project project) {
        this.project.set(project);
    }

    /**
     * A creator is used to inject the project ID into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        private final String projectID;

        public Factory(@NonNull Application application, String projectID) {
            this.application = application;
            this.projectID = projectID;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            //noinspection unchecked
            return (T) new ProjectDetailsViewModel(application, projectID);
        }
    }
}
