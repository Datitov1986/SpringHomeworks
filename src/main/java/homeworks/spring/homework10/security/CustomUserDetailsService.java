package homeworks.spring.homework10.security;

//import homeworks.spring.homework10.model.Reader;
//import homeworks.spring.homework10.repository.ReaderRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;

import java.util.List;

//@Service
//@RequiredArgsConstructor
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final ReaderRepository readerRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Reader reader = readerRepository.findByLogin(username)
//                .orElseThrow(() -> new UsernameNotFoundException(username));
//
//
//        return new org.springframework.security.core.userdetails.User(reader.getLogin(), reader.getPassword(), List.of(
//                new SimpleGrantedAuthority(reader.getRole())
//        ));
//    }
//}
