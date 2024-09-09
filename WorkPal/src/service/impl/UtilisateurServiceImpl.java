package service.impl;

import entity.Utilisateur;
import repository.UtilisateurRepository;
import service.UtilisateurService;

import javax.management.RuntimeMBeanException;
import java.util.List;

public class UtilisateurServiceImpl implements UtilisateurService {
    private final UtilisateurRepository userRepository;

    public UtilisateurServiceImpl(UtilisateurRepository repository)
    {
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
    public void deleteUser(Utilisateur utilisateur)
    {
        userRepository.deleteById(utilisateur.getId());
    }

    @Override
    public void updateUser(Utilisateur utilisateur)
    {
        userRepository.update(utilisateur);
    }
}
