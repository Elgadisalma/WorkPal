package org.example.service;

import org.example.entity.Espace;

import java.util.List;
import java.util.Optional;

public interface EspaceService {
    void createEspace(Espace espace);
    void deleteEspace(Long id);
    void updateEspace(Espace espace, Long id);
    Optional<Espace> getSpace(Long id);
    List<Espace> getSpaces();

}
