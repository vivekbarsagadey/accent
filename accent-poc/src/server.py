from flask import Flask
from flask_restplus import Api
from flask_cors import CORS


app = Flask(__name__)
CORS(app)

api = Api(app, catch_all_404s=True)

# api.add_resource(UserFileuploadscontroller,'/api/diabetes/dataset/upload',endpoint="dataset/upload")

if __name__ == '__main__':
    app.run(debug=True,port=5000)