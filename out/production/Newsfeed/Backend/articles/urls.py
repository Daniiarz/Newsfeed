from django.urls import path
from .views import ListArticles, ArticleDetail

urlpatterns = [
    path("articles/", ListArticles.as_view(), name="articles"),
    path("articles/<int:pk>/", ArticleDetail.as_view(), name="article-detail"),
]
