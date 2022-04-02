package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceIMPL extends AbstractMapServices<String, ProjectDTO> implements ProjectService {
    TaskService taskService;

    public ProjectServiceIMPL(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ProjectDTO save(ProjectDTO object) {
        if (object.getStatus() == null) {
            object.setStatus(Status.OPEN);
        }
        return super.save(object.getProjectCode(), object);
    }

    @Override
    public ProjectDTO findById(String id) {
        return super.findById(id);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(ProjectDTO object) {
        if (object.getStatus() == null) {
            object.setStatus(findById(object.getProjectCode()).getStatus());
        }

        super.update(object.getProjectCode(), object);
    }

    @Override
    public void deleteById(String id) {
        super.deleteById(id);
    }

    @Override
    public void complete(ProjectDTO projectDTO) {
        projectDTO.setStatus(Status.COMPLETE);
        super.save(projectDTO.getProjectCode(), projectDTO);
    }

    @Override
    public List<ProjectDTO> findAllNonCompletedProjects() {
        return findAll().stream().filter(project -> !project.getStatus().equals(Status.COMPLETE)).collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {
        List<ProjectDTO> projectList =
                findAll()
                        .stream()
                        .filter(project -> project.getAssignedManager().equals(manager))
                        .map(project -> {
                            System.out.println(project.getProjectCode());

                            List<TaskDTO> taskList = taskService.findTasksByManager(manager);

                            int completeTaskCounts = (int)taskList.stream().filter(t ->  t.getProject().equals(project) && t.getTaskStatus() == Status.COMPLETE).count();

                            int unfinishedTaskCounts = (int)taskList.stream().filter(t ->  t.getProject().equals(project) && t.getTaskStatus() != Status.COMPLETE).count();

                            project.setCompleteTaskCounts(completeTaskCounts);
                            project.setUnfinishedTaskCounts(unfinishedTaskCounts);

                            return project;

                        }).collect(Collectors.toList());


        System.out.println(projectList);
        return projectList;

    }

}
