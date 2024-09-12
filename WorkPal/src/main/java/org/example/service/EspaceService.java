package org.example.service;

import org.example.entity.Espace;

import java.util.List;
import java.util.Optional;

public interface EspaceService {
    void createEspace(Espace espace);
    void deleteEspace(Long id);
//    void updateSpace(Espace espace);
//    Optional<Espace> getSpace(String spaceName);
//    List<Espace> getSpaces();

}
