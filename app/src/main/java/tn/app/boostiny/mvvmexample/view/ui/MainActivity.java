package tn.app.boostiny.mvvmexample.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;

import android.net.Uri;
import android.os.Bundle;

import tn.app.boostiny.mvvmexample.R;
import tn.app.boostiny.mvvmexample.model.Project;

public class MainActivity  extends AppCompatActivity implements ProjectListFragment.OnFragmentInteractionListener , ProjectDetailsFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add project list fragment if this is first creation
        if (savedInstanceState == null) {
            ProjectListFragment fragment = new ProjectListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, ProjectListFragment.TAG).commit();
        }
    }


    /** Shows the project detail fragment */
    public void show(Project project) {
        ProjectDetailsFragment projectFragment = ProjectDetailsFragment.forProject(project.name);

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("project")
                .replace(R.id.fragment_container,
                        projectFragment, null).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
