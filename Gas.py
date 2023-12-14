import pygame
import random

# pygame setup
pygame.init()
screen = pygame.display.set_mode((700, 700))
clock = pygame.time.Clock()
running = True


class Particle(pygame.sprite.Sprite):
    def __init__(self, pos_x, pos_y):
        super().__init__()
        self.KineticEnergy = 1.6398E-17
        self.mass = 9.11E-31
        self.velocity = 6.0E6
        self.changex = 0
        self.changey = 0
        self.right = True
        self.up = True
        self.red = 255
        self.green = 0
        self.blue = 255
        self.image = pygame.Surface((40, 40))
        self.image.fill((self.red, self.green, self.blue))
        self.mask = pygame.mask.from_surface(self.image)
        self.rect = self.image.get_rect(center=(pos_x, pos_y))
        self.collided = False 
        self.collide_timer = 0  

    def update(self):
        self.image.fill((self.red, self.green, self.blue))

        self.rect.x += self.changex
        self.rect.y += self.changey

        if self.right:
            self.changex = 3
        else:
            self.changex = -3

        if self.rect.x > 685:
            self.right = False
        elif self.rect.x < 15:
            self.right = True

        if self.up:
            self.changey = 4
        else:
            self.changey = -4

        if self.rect.y > 685:
            self.up = False
        elif self.rect.y < 15:
            self.up = True


        self.collide_timer -= 1

        if self.collide_timer <= 0:
            self.collided = False
            self.collide_timer = 0

    def collide(self, particle_group):
        if not self.collided:
            collided_particle = pygame.sprite.spritecollideany(self, particle_group)
            if collided_particle and collided_particle != self:
                self.right = not self.right
                self.up = not self.up
                self.collided = True
                
                self.red  = random.randint(1,240)
                self.green = random.randint(1,240)
                self.blue = random.randint(1,240)
                self.image.fill((self.red, self.green, self.blue))
                # Set the timer to 30 frames
                self.collide_timer = 5
                self.kinetic()
        else:
            # Check if particles have moved away from each other
            if not pygame.sprite.spritecollideany(self, particle_group):
                self.collided = False
                self.collide_timer = 0
        
    def kinetic(self):
        self.KineticEnergy = (1/2 * self.mass * (self.velocity**2))
        return self.KineticEnergy
    
    def display(self):
        self.test_font = pygame.font.Font('Pixeltype.ttf', 12)
        score_surface = self.test_font.render(f'{self.KineticEnergy:.2e}', False, (255, 255, 255))
        screen.blit(score_surface, (self.rect.x, self.rect.y))
        
    
class Container(pygame.sprite.Sprite):
    def __init__(self,pos_x, pos_y):
        super().__init__()
        self.image = pygame.Surface((680, 680))
        self.image.fill((255,0,0))
        self.mask = pygame.mask.from_surface(self.image)
        self.rect = self.image.get_rect(center=(pos_x, pos_y))



particle_group = pygame.sprite.Group()
#container_group = pygame.sprite.Group()
#container_group.add(Container(350,350))
for i in range(1, 3):
    randx = random.randint(60, 660)
    randy = random.randint(60, 660)
    z = Particle(randx, randy)
    particle_group.add(z)

while running:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
    
    
        if event.type == pygame.MOUSEBUTTONDOWN:
            if event.button ==1:
                randx = random.randint(60, 660)
                randy = random.randint(60, 660)
                z = Particle(randx, randy)
                particle_group.add(z)
                
        if event.type == pygame.MOUSEBUTTONDOWN:
            if event.button ==3:
                particle_group.remove(z)
                
    screen.fill("Black")
    particle_group.draw(screen)
    #container_group.draw(screen)
    
        
    for p in particle_group:
        p.update()
        p.collide(particle_group)
        p.display()

    pygame.display.flip()
    clock.tick(60)

pygame.quit()

