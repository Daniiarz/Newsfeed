from rest_framework import serializers
from .models import Article


class ArticleSerializer(serializers.HyperlinkedModelSerializer):

    class Meta:
        model = Article
        fields = (
            "url",
            "pk",
            'title',
            "description_or_date_pub",
            'link',
            "image_link",
        )
