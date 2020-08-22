import os
import json
from flask import Flask, jsonify, make_response
from flask_restful import Resource, Api
from flask_sqlalchemy import SQLAlchemy
import sys, traceback
import json
import logging
import decimal

logging.basicConfig(level=logging.DEBUG)
logger = logging.getLogger(__name__)

from serializers import AlchemyEncoder

app = Flask(__name__)
api = Api(app)

app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://{}:{}@{}/{}'.format(
    os.getenv('DB_USER', 'ops'),
    os.getenv('DB_PASSWORD', 'ops'),
    os.getenv('DB_HOST', 'mysqldb'),
    os.getenv('DB_NAME', 'ops')
)
db = SQLAlchemy(app)


class DecimalEncoder(json.JSONEncoder):
    def _iterencode(self, o, markers=None):
        if isinstance(o, decimal.Decimal):
            # wanted a simple yield str(o) in the next line,
            # but that would mean a yield on the line with super(...),
            # which wouldn't work (see my comment below), so...
            return (str(o) for o in [o])
        return super(DecimalEncoder, self)._iterencode(o, markers)

class Total(Resource):
    def get(self):
        try:
            result = db.engine.execute("select ifnull(sum(value),0) from balance")
            total = [row[0] for row in result]
            logger.info("TOTAL = {}".format(total))
            json_data = json.dumps(total, ensure_ascii=False, default=str)
             
            return make_response(json_data, 200) 
        except Exception as error:
            logger.exception(error)
            return [], 500


        

api.add_resource(Total, '/total')

if __name__ == '__main__':
    app.run(host="0.0.0.0", debug=True)
