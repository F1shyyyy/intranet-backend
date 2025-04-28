package web.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import shared.Users;
import web.entity.User;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserRepositoryRmiImpl implements UserRepository{

    private final Users connection;

    public UserRepositoryRmiImpl() {
        try {
            this.connection = (Users)Naming.lookup("rmi://localhost:1099/users");
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends User> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<User> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public User getOne(Long id) {
        return getById(id);
    }

    @Override
    public User getById(Long id) {
        try {
            return new User(this.connection.getUserByID(id));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getReferenceById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends User> Optional<S> findOne(Example<S> example) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends User> long count(Example<S> example) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends User> boolean exists(Example<S> example) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends User> S save(S entity) {
        try {
            if (this.connection.saveUser(entity.getSharedUser())){
                return entity;
            } else {
                return null;
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            shared.User user = this.connection.getUserByID(id);
            if (user == null){
                return Optional.empty();
            }else {
                return Optional.of(new User(user));
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsById(Long id) {
        try {
            return this.connection.getUserByID(id) != null;
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        try {
            return this.connection.getUsers().stream().map(user -> new User(user)).toList();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAllById(Iterable<Long> longs) {
        return List.of();
    }

    @Override
    public long count() {
        try {
            return this.connection.getUsers().size();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(User entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> findAll(Sort sort) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        throw new UnsupportedOperationException();
    }
}
