namespace java simple

struct User {
  1: string id,
  2: string name,
  3: string password
}

service UserService {
  void store(1: User user),
  User get(1: string name)
}
