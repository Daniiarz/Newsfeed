from rest_framework import authentication, permissions
from rest_framework.generics import ListCreateAPIView, RetrieveUpdateDestroyAPIView
from .serializers import ArticleSerializer
from .models import Article
# Create your views here.


class ListArticles(ListCreateAPIView):
    model = Article
    serializer_class = ArticleSerializer
    authentication_classes = (authentication.TokenAuthentication,)
    permission_classes = (permissions.IsAuthenticated,)

    def perform_create(self, serializer):
        """Create a new ingredient"""
        serializer.save(user=self.request.user)

    def get_queryset(self):
        """Return objects for the current authenticated user only"""
        return Article.objects.filter(user=self.request.user)


class ArticleDetail(RetrieveUpdateDestroyAPIView):
    serializer_class = ArticleSerializer
    model = Article
    queryset = Article.objects.all()
    authentication_classes = (authentication.TokenAuthentication,)
    permission_classes = (permissions.IsAuthenticated,)


