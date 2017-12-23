package mx.infotec.dads.kukulkan.shell.domain;

import java.io.Serializable;

import mx.infotec.dads.kukulkan.metamodel.foundation.ProjectConfiguration;

/**
 * ProjectContext, It has the main configuration of the Shell
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class ProjectContext implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private ProjectConfiguration project;

    public ProjectConfiguration getProject() {
        return project;
    }

    public void setProject(ProjectConfiguration project) {
        this.project = project;
    }

}
