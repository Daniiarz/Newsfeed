from rest_framework import serializers
from . import models


class UserSerializer(serializers.HyperlinkedModelSerializer):

    class Meta:
        model = models.User
        fields = (
            "ulr",
            "pk",
            'first_name',
            "last_name",
            'email',
        )
