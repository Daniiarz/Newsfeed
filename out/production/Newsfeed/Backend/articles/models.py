from django.conf import settings
from django.db import models
from django.urls import reverse

# Create your models here.


class Article(models.Model):
    user = models.ForeignKey(
        settings.AUTH_USER_MODEL,
        on_delete=models.CASCADE
    )
    title = models.CharField(max_length=255)
    description_or_date_pub = models.CharField(max_length=255)
    link = models.CharField(max_length=255)
    image_link = models.CharField(max_length=255, blank=True, null=True)

    def get_absolute_url(self):
        return reverse("office-detail", args=[str(self.id)])

    def __str__(self):
        return self.title
