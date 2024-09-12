package org.example.service.impl;

import org.example.entity.Espace;
import org.example.repository.EspaceRepository;
import org.example.service.AuthService;
import org.example.service.EspaceService;


public class EspaceServiceImpl implements EspaceService {

    private final EspaceRepository espaceRepository;
    private final AuthService authService;

    public EspaceServiceImpl(EspaceRepository espaceRepository, AuthService authService) {
        this.espaceRepository = espaceRepository;
        this.authService = authService;
    }

    @Override
    public void createEspace(Espace espace)
    {
        if(authService.isModerator()){
            espaceRepository.save(espace);
            System.out.println("Espace créé avec succès !");
        }
        System.out.println("no access");
    }

    @Override
    public void deleteEspace(Long id)
    {
        if(authService.isModerator())
        {
            espaceRepository.deleteById(id);
            System.out.println("deleted successfully");
        }
        System.out.println("no access");
    }
//
//    @Override
//    public void updateSpace(Espace espace)
//    {
//
//    }
//
//    @Override
//    public Optional<Espace> getSpace(String name)
//    {
//        return null;
//    }
//
//    @Override
//    public List<Espace> getSpaces()
//    {
//    }


}
