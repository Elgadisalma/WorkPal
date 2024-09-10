package org.example.service.impl;

import org.example.entity.Utilisateur;
import org.example.repository.UtilisateurRepository;
import org.example.service.UtilisateurService;

import java.util.List;

public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository userRepository;

    public UtilisateurServiceImpl(UtilisateurRepository repository) {
        this.userRepository = repository;
    }

    @Override
    public List<Utilisateur> findAllUsers()
    {
        return userRepository.findAll();
    }

    @Override
    public Utilisateur findUserById(Long id)
    {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public void saveUser(Utilisateur utilisateur)
    {
        userRepository.save(utilisateur);
    }

    @Override
    public void deleteUser(Long id)
    {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(Utilisateur utilisateur, Long id)
    {
        utilisateur.setId(id);
        userRepository.update(utilisateur);
    }

    @Override
    public Utilisateur findUserByName(String fullName)
    {
        return userRepository.findByName(fullName)
                .orElseThrow(() -> new RuntimeException("not found"));
    }
}
