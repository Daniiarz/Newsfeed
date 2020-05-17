from django.db import models
from django.contrib.auth.models import AbstractBaseUser, BaseUserManager

# Create your models here.


class UserManager(BaseUserManager):

    def create_user(self, email, first_name, last_name, phone_number_region, phone_number,
                    password=None, is_active=True, is_staff=False, is_admin=True):

        if not email:
            raise ValueError("Users must have an email address")
        if not password:
            raise ValueError("Users must have a password")
        if not first_name or not last_name:
            raise ValueError("Users must have full name")

        user_obj = self.model(
            email=self.normalize_email(email),
            first_name=first_name,
            last_name=last_name,
            phone_number_region=phone_number_region,
            phone_number=phone_number
        )

        user_obj.set_password(password)
        user_obj.staff = is_staff
        user_obj.admin = is_admin
        user_obj.active = is_active
        user_obj.save(using=self._db)

        return user_obj

    def create_staffuser(self, first_name, last_name, email, phone_number_region, phone_number, password):
        user = self.create_user(
            email,
            first_name,
            last_name,
            phone_number_region,
            phone_number,
            password=password,
            is_staff=True
        )
        return user

    def create_superuser(self, first_name, last_name, email, phone_number_region, phone_number, password):
        user = self.create_user(
            email,
            first_name,
            last_name,
            phone_number_region,
            phone_number,
            password=password,
            is_staff=True,
            is_admin=True
        )
        return user


class User(AbstractBaseUser):
    email = models.EmailField(max_length=255, unique=True)
    first_name = models.CharField(max_length=100, null=True, blank=True)
    last_name = models.CharField(max_length=100, null=True, blank=True)

    active = models.BooleanField(default=True)
    staff = models.BooleanField(default=False)
    admin = models.BooleanField(default=False)

    USERNAME_FIELD = "email"
    REQUIRED_FIELDS = ["first_name", "last_name"]  # python manage.py createsuperuser

    objects = UserManager()

    def has_perm(self, perm, obj=None):
        return True

    def has_module_perms(self, app_label):
        return True

    def __str__(self):
        return self.email

    def get_first_name(self):
        return self.first_name

    def get_last_name(self):
        return self.last_name

    def get_full_name(self):
        return f"{self.first_name} {self.last_name}"

    @property
    def is_staff(self):
        return self.staff

    @property
    def is_admin(self):
        return self.admin

    @property
    def is_active(self):
        return self.active



