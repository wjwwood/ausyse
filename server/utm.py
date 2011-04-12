from pyproj import Proj, transform

latlong_proj = Proj(proj='latlong',datum='WGS84')
utm_proj = Proj(proj='utm',zone=16,datum='WGS84')

def latlong2utm(lat, lng):
    """Converts lat long to utm  x and y.  Assumes zone s16"""
    return transform(latlong_proj, utm_proj, lng, lat)

def utm2latlong(x, y):
    """Converts utm x and y to lat and long. Assumes zone s16"""
    return transform(utm_proj, latlong_proj, x, y)

if __name__ == '__main__':
    lat1 = 32.597
    long1 = -85.469
    
    utmx1, utmy1 = transform(latlong_proj, utm_proj, long1, lat1)
    
    print utmx1, utmy1
    
    lat2, long2 = transform(utm_proj, latlong_proj, utmx1, utmy1)
    
    print lat2, long2