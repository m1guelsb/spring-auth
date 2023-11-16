- add spring security dependency

- add 3 convention tables: 
users, user_permission, permission
users: {
  user_name
  password
  account_non_expired
  account_non_locked
  credentials_non_expired
  enabled
}

user_permission {
  id_user
  id_permission
}

permissions {
  description
}


